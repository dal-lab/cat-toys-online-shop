module.exports = {
  module: {
    rules: [
      {
        test: /\.m?js$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: [
              [
                '@babel/preset-env',
                {
                  useBuiltIns: true,
                },
              ],
              '@babel/preset-react',
            ],
          },
        },
      },
    ],
  },
  devServer: {
    port: 3000,
  },
};
