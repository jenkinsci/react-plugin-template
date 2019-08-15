const mkdirp = require('mkdirp');
const ncp = require('ncp').ncp;
mkdirp('../webapp/page', function(err) {

  // path exists unless there was an error

});


ncp.limit = 16;
ncp("./build/", "../webapp/page", function (err) {
  if (err) {
    return console.error(err);
  }
  console.log('done!');
});
