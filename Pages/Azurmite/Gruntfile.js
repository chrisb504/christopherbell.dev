module.exports = function (grunt) {

    "use strict";

    grunt.loadNpmTasks("grunt-contrib-concat");
    grunt.loadNpmTasks("grunt-contrib-uglify");
    grunt.loadNpmTasks('grunt-mamp');

    grunt.initConfig({
        pkg: grunt.file.readJSON("package.json"),
        // TODO: add grunt command to run test before concat and uglify
        // grunt task to merge files
        concat: {
            js: {
               src: [
                   "js/src/wrappers/begin-main-wrapper.js",

                        "js/src/define-game.js",
                        "js/src/variables/strings.js",
                        "js/src/variables/integers.js",
                        "js/src/variables/level-components.js",
                        "js/src/variables/characters.js",
                        "js/src/variables/collectibles.js",
                        "js/src/variables/music.js",
                        "js/src/variables/controls.js",

                        "js/src/wrappers/begin-preload-wrapper.js",
                            "js/src/preload/preload-images.js",
                            "js/src/preload/preload-music.js",
                            "js/src/preload/preload-spritesheets.js",
                            "js/src/preload/preload-tilemaps.js",
                        "js/src/wrappers/end-preload-wrapper.js",

                        "js/src/wrappers/begin-create-wrapper.js",
                            "js/src/create/init-level-object-components.js",
                            "js/src/create/camera.js",
                            "js/src/create/physics.js",
                            "js/src/create/object-animations.js",
                            "js/src/create/hud.js",
                            "js/src/create/spawn-funky-flowers.js",
                            "js/src/create/init-music.js",
                            "js/src/create/init-controls.js",
                        "js/src/wrappers/end-create-wrapper.js",

                        "js/src/wrappers/begin-update-wrapper.js",
                             "js/src/update/collision-physics.js",
                             "js/src/update/scroll-background.js",
                             "js/src/update/update-player-controls.js",
                             "js/src/update/collect-diamond.js", 
                             "js/src/update/update-funky-flower-ai.js",
                        "js/src/wrappers/end-update-wrapper.js",

                        "js/src/controls/player-controls.js",
                        "js/src/update/detect-bullet-hit.js",
                        "js/src/update/is-player-dead.js",
                        "js/src/update/subtract-player-health.js",
                        "js/src/artificial-intelligence/funky-flower-ai.js",
                   "js/src/wrappers/end-main-wrapper.js"
               ],

                dest: "js/development/main.js"
            }
        },
        // grunt task to minify files
        uglify: {
            js: {
                src: "js/development/main.js",
                dest: "js/production/main.min.js"
            }
        }
    });

    // a silly task
    grunt.registerTask("azurmite", function () {
        console.log("Azurmite original G!!!");
    });

    // the default task, run when "grunt" is typed on command line while in same directory as Gruntfile.js
    grunt.registerTask("default", [
        "azurmite",
        "concat",
        "uglify"
    ]);
};
