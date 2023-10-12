const path = require('path');

module.exports = {
  entry: './src/main/react/jsx/App.jsx', // Le point d'entrée de votre application ReactJS
  output: {
    filename: 'nirahtech-erp-webapp.min.js', // Le nom du fichier de sortie
    path: path.resolve(__dirname, 'src/main/webapp/js') // Le répertoire de sortie
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader'
        }
      }
    ]
  },
  resolve: {
    extensions: ['.js', '.jsx'] // Ajoutez cette ligne pour les fichiers JSX
  }
};
