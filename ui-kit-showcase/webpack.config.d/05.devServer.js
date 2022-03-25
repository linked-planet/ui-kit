;(function (config) {
    if (config.devServer) {
        config.devServer.host = 'localhost'
        config.devServer.client = {
            overlay: {
                errors: true,
                warnings: false
            }
        }
    }
})(config);
