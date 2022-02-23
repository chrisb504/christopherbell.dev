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
        
        /**
         * The list for React components starts here. Please keep these in 
         * alphabetical order.
         */
        './src/main/resources/static/js/components/blog/blog.js',
        './src/main/resources/static/js/components/blogtags/blogtags.js',
        './src/main/resources/static/js/components/footer/footer.js',
        './src/main/resources/static/js/components/gallery/gallery.js',
        './src/main/resources/static/js/components/nav/nav.js',
        './src/main/resources/static/js/components/nav/navitems/navdropdown/navdropdown.js',
        './src/main/resources/static/js/components/nav/navitems/navdropdown/navdropdownitem/navdropdownitem.js',
        './src/main/resources/static/js/components/nav/navitems/navstditem/navstditem.js',
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
