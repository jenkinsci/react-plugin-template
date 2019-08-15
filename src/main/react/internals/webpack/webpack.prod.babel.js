// Important modules this config uses
const webpack = require('webpack')
const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const WebpackPwaManifest = require('webpack-pwa-manifest');
const OfflinePlugin = require('offline-plugin');
const { HashedModuleIdsPlugin } = require('webpack');
const TerserPlugin = require('terser-webpack-plugin');
const CompressionPlugin = require('compression-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin')
module.exports = require('./webpack.base.babel')({
  mode: 'production',

  // In production, we skip all hot-reloading stuff
  entry: [
    require.resolve('react-app-polyfill/ie11'),
    path.join(process.cwd(), 'app/app.js'),
  ],

  // Utilize long-term caching by adding content hashes (not compilation hashes) to compiled assets
  output: {
    filename: '[name].js',
    chunkFilename: '[name].chunk.js',
  },

  optimization: {
    minimize: true,
    minimizer: [
      new TerserPlugin({
        terserOptions: {
          warnings: false,
          compress: {
            comparisons: false,
          },
          parse: {},
          mangle: true,
          output: {
            comments: false,
            ascii_only: true,
          },
        },
        parallel: true,
        cache: true,
        sourceMap: true,
      }),
    ],
    nodeEnv: 'production',
    sideEffects: true,
    concatenateModules: true,
    runtimeChunk: false,
  },

  plugins: [

    // Minify and optimize the index.html
    new HtmlWebpackPlugin({
      template: 'app/index.html',
      minify: {
        removeComments: true,
        collapseWhitespace: true,
        removeRedundantAttributes: true,
        useShortDoctype: true,
        removeEmptyAttributes: true,
        removeStyleLinkTypeAttributes: true,
        keepClosingSlash: true,
        minifyJS: true,
        minifyCSS: true,
        minifyURLs: true,
      },
      inject: 'body',
    }),


    // Put it in the end to capture all the HtmlWebpackPlugin's
    // assets manipulations and do leak its manipulations to HtmlWebpackPlugin
    new CopyWebpackPlugin([
      {from:'build/main.js',to:'../../webapp/js/'},
      {from:'build/index.html',to:'../../webapp/js/'},

    ])
  ],

  performance: {
    assetFilter: assetFilename =>
      !/(\.map$)|(^(main\.|favicon\.))/.test(assetFilename),
  },
});
