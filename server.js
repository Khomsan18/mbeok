let express = require('express');
let app = express();
//let bodyParser = require('body-parser');
let mysql = require('mysql');

app.use(express.json());
app.use(express.urlencoded({
    extended: true
}));

app.get('/', function (req, res) {
    return res.send({ error: true, message: 'Test User Web API' })
});

let dbConn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'mbeok'
});

dbConn.connect();

app.get('/alluser', function (req, res) {
    dbConn.query('SELECT * FROM users', function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});

/*app.post('/std', function (req, res) {

    let std = req.body

    if (!std) {
        return res.status(400).send({ error: true, message: 'Please provide student ' });
    }

    dbConn.query("INSERT INTO student SET ? ", std, function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    });
});*/
app.get('/user/:user_id', function (req, res) {
    let user_id = req.params.user_id;
    if (!user_id) {
        return res.status(400).send({ error: true, message: 'Please provide user id' });
    }
    dbConn.query("SELECT * FROM users WHERE user_id = ?", user_id, function (error, results, fields) {
        if (error) throw error;
        if (results[0]) {
            return res.send(results[0]);
        } else {
            return res.status(400).send({ error: true, message: 'User id Not Found!!' });
        }
    });          
})

app.put('/user/:user_id',function(req,res){
    let user_id = req.params.user_id;
    let user = req.body
    if(!user_id || !user){
        return res.status(400).send({ error: true, message: 'Please provide user id and user data' }); 
    }

    dbConn.query('UPDATE users SET ? WHERE user_id = ?', [user, user_id], function(error, results, fields) {
        if (error) throw error;
        
        return res.send({ error: false, message: 'User has been updated seccessfully' });
       
    });    
})

app.delete('/user/:user_id', function(req,res){
    let user_id = req.params.user_id;
    if (!user_id) {
        return res.status(400).send({ error: true, message: 'Please provide user id' });
    }
    dbConn.query('DELETE FROM users WHERE user_id = ?', user_id, function(error, results, fields) {
        if (error) throw error;
        
        return res.send({ error: false, message: 'User has been deleted seccessfully' });
       
    });    
})
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

app.get("/allebook", function (req, res) {
    dbConn.query("SELECT * FROM ebooks", function (error, results, fields) {
      if (error) throw error;
      return res.send(results);
    });
  });
  
  app.post("/ebk", function (req, res) {
    var ebook = req.body;
  
    if (!ebook) {
      return res
        .status(400)
        .send({ error: true, message: "Please provide ebooks " });
    }
  
    dbConn.query(
      "INSERT INTO ebooks SET ? ",
      ebook,
      function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
      }
    );
  });
  
//set port
app.listen(3000, function () {
    console.log('Node app is running on port 3000');
});

module.exports = app;