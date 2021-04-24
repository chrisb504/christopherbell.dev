// Imports
const path = require('path');

// Variables
const outputDirectory = './src/main/resources/static/js';


module.exports = {
    mode: 'development',
    entry: [
        'babel-polyfill',
        './src/main/resources/static/js/whats-for-lunch/maincontroller.mjs',
        './src/main/resources/static/js/whats-for-lunch/restaurantcontroller.mjs',
        './src/main/resources/static/js/react/nav/index.js',
        './src/main/resources/static/js/react/footer/index.js',
        './src/main/resources/static/js/react/blog/index.js'
    ],
    output: {
        path: path.join(__dirname, outputDirectory),
        filename: 'main.js'
    },
    module: {
        rules: [{
            test: /\.js$/,
            exclude: /node_modules/,
            use: {
                loader: 'babel-loader'
            }
        },
        {
            test: /\.css$/i,
            use: [
                "style-loader",
                    {
                        loader: "css-loader",
                        options: {
                            importLoaders: 1,
                        },
                    }
                ]
        }]
    },
    devServer: {
        port: 3000,
        open: true,
        proxy: {
            '/': 'http://localhost:8080'
        }
    }
};
