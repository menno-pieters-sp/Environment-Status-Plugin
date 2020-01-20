# Environment-Status-Plugin

This plugin is provided as a simple example plugin for SailPoint IdentityIQ. The plugin can show a small logo image in the top right corner of the screen to indicate the status of the environment. This could be the development status for example, like a sandbox, development environment, test, etc. up to production, but could also indicate that an environment is in maintenance mode. 

The available modes can be configured in a Config object, which is included in the plugin and deployed on the first installation. The Config object maps status codes to the location of the image to use:
* If the location starts with http:// or https://, the full location will be used,
* If the location starts with /, the location will be assumed to be relative to the IdentityIQ installation root,
* Otherwise, the location is assumed to be relative to the plugin root.

Images should be 100 pixels wide and 20 pixels in height.
