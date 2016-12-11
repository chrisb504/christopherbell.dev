var gulp = require('gulp'),
    uglify = require('gulp-uglify'),
    rename = require('gulp-rename');

var concat = require('gulp-concat');
//var gulpLoadPlugins = require('gulp-load-plugins');
//var plugins = gulpLoadPlugins();


gulp.task('scripts', function() {
    gulp.src([
        "js/Survive/Wrappers/Survive-Wrapper-Start.js",
        "js/Survive/Player.js",
        "js/Survive/Wrappers/Survive-Wrapper-End.js"])
        //.pipe(uglify())
        .pipe(concat("Survive.js"))
        //.pipe(uglify())
        .pipe(rename({suffix:'.min'}))
        .pipe(gulp.dest('js/Survive/build'));
});


//  Watch Tasks
//gulp.task('watch', function() {
//    gulp.watch('js/**/.js', ['scripts'] )
//});

gulp.task('default', ['scripts']);
	// place code for your default task here
	//gulp.src("js/Survive/Wrappers/Survive-Wrapper-Start")
	  //  .pipe(concat("main.all.js"));

//	gulp.src('js/**.js')
//		.pipe(concat("main.all.js"))
//		.pipe(uglify())
//		.pipe(gulp.dest('js'));
//
//
//
//
//	gulp.src('css/**.css')
//		.pipe(concat("main.all.css"))
//		.pipe(uglify())
//		.pipe(gulp.dest('css'));
//});
