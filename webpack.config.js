module.exports = {
    mode: 'production',
    entry: {
        index: './src/js/index.js',
        //codemirrormodes: './src/js/codemirror_modes.js'
    },
    output: {
        filename: '[name].bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader'],
            },
        ]
    },
}
