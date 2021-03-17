function() {
  var env = karate.env; // get java system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev'; // a custom 'intelligent' default
  }
  var config = { // base config JSON
    baseUrl: 'http://localhost:7171'
  };
  if (env == 'stage') {
    // over-ride only those that need to be
    config.baseUrl = '';
  } else if (env == 'e2e') {
    config.baseUrl = '';
  } else if (env == 'prod') {
  // In our case I'm setting localhost url for prod too
    config.baseUrl =  'http://localhost:7171'
  }
  // don't waste time waiting for a connection or if servers don't respond within 5 seconds
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  return config;
}