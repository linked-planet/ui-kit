;(function (config) {
    config.resolve.modules.push(
        "build/processedResources/js/main"
    );
    config.resolve.fallback = {
        "path": require.resolve("path-browserify")
    }
})(config);
