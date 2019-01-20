const path = require('path');

const outputDirectory = '/public/dist';

module.exports = {
    mode: 'development',
    entry: [
        'babel-polyfill',
        './public/js/blog/blog.js',
        './apps/header/js/header.js',
        './apps/footer/js/footer.js',
        './public/js/whats-for-lunch/maincontroller.mjs',
        './public/js/whats-for-lunch/restaurantcontroller.mjs',
        './apps/react/index.js'
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
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    },
    devServer: {
        port: 3000,
        open: true,
        proxy: {
            '/': 'http://localhost:8080'
        }
    }
};