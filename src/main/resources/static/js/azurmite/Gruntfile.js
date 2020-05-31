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
                   "public/js/src/wrappers/begin-main-wrapper.js",

                        "public/js/src/define-game.js",
                        "public/js/src/variables/strings.js",
                        "public/js/src/variables/integers.js",
                        "public/js/src/variables/level-components.js",
                        "public/js/src/variables/characters.js",
                        "public/js/src/variables/collectibles.js",
                        "public/js/src/variables/music.js",
                        "public/js/src/variables/controls.js",

                        "public/js/src/wrappers/begin-preload-wrapper.js",
                            "public/js/src/preload/preload-images.js",
                            "public/js/src/preload/preload-music.js",
                            "public/js/src/preload/preload-spritesheets.js",
                            "public/js/src/preload/preload-tilemaps.js",
                        "public/js/src/wrappers/end-preload-wrapper.js",

                        "public/js/src/wrappers/begin-create-wrapper.js",
                            "public/js/src/create/init-level-object-components.js",
                            "public/js/src/create/camera.js",
                            "public/js/src/create/physics.js",
                            "public/js/src/create/object-animations.js",
                            "public/js/src/create/hud.js",
                            "public/js/src/create/spawn-funky-flowers.js",
                            "public/js/src/create/init-music.js",
                            "public/js/src/create/init-controls.js",
                        "public/js/src/wrappers/end-create-wrapper.js",

                        "public/js/src/wrappers/begin-update-wrapper.js",
                             "public/js/src/update/collision-physics.js",
                             "public/js/src/update/scroll-background.js",
                             "public/js/src/update/update-player-controls.js",
                             "public/js/src/update/collect-diamond.js", 
                             "public/js/src/update/update-funky-flower-ai.js",
                        "public/js/src/wrappers/end-update-wrapper.js",

                        "public/js/src/controls/player-controls.js",
                        "public/js/src/update/detect-bullet-hit.js",
                        "public/js/src/update/is-player-dead.js",
                        "public/js/src/update/subtract-player-health.js",
                        "public/js/src/artificial-intelligence/funky-flower-ai.js",
                   "public/js/src/wrappers/end-main-wrapper.js"
               ],

                dest: "public/js/development/main.js"
            }
        },
        // grunt task to minify files
        uglify: {
            js: {
                src: "public/js/development/main.js",
                dest: "public/js/production/main.min.js"
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
