const port = process.env.PORT || 3000,
express = require('express'),
app = express(),
http = require('http').Server(app),
io = require('socket.io')(http),
path = require('path'),
bodyParser = require('body-parser');

app.use(bodyParser.json());

io.on('connection', function (client) {
  client.on('startGame', function(data) {
      io.emit('start', {});
  });
  client.on('disconnect', function(data) {
    console.log('happen');
  });
});

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname + '/index.html'));
});

app.post('/api/login', (req, res) => {
  io.emit('update', req.body);
  res.sendStatus(200);
});

app.post('/api/score', (req, res) => {
  io.emit('update', req.body);
  res.sendStatus(200);
});

http.listen(port);
