// Imports
const path = require('path');

// Variables
const outputDirectory = './src/main/resources/static/js';

module.exports = {
    mode: 'development',
    entry: [
        'bootstrap/dist/css/bootstrap.min.css', // Bootstrap CSS
        'bootstrap/dist/js/bootstrap.bundle.min.js', // Bootstrap JS (includes Popper.js)
        './src/main/resources/static/js/app.js'
    ],
    output: {
        path: path.join(__dirname, outputDirectory),
        filename: 'main.js'
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [
                            '@babel/preset-env'
                        ],
                        plugins: [
                            '@babel/plugin-proposal-class-properties',
                            '@babel/plugin-transform-runtime'
                        ]
                    }
                }
            },
            {
                test: /\.css$/i,
                use: [
                    'style-loader',
                    'css-loader',
                    'postcss-loader'  // Added PostCSS for CSS optimizations
                ]
            },
            {
                test: /\.(woff|woff2|eot|ttf|otf|svg)$/, // For font and SVG assets used by Bootstrap
                type: 'asset/resource' // Handles assets like fonts/images
            }
        ]
    },
    devServer: {
        port: 3000,
        open: true,
        proxy: {
            '/': 'http://localhost:8080'
        }
    },
    resolve: {
        extensions: ['.js']
    },
    // For better debugging support (optional)
    devtool: 'source-map'  // Easier debugging in development
};
