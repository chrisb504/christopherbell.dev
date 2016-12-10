var gulp = require('gulp');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');
//var gulpLoadPlugins = require('gulp-load-plugins');
//var plugins = gulpLoadPlugins();

gulp.task('default', function() {
	// place code for your default task here
	gulp.src('js/**.js')
		.pipe(concat("main.all.js"))
		.pipe(uglify())
		.pipe(gulp.dest('js'));

	gulp.src('css/**.css')
		.pipe(concat("main.all.css"))
		.pipe(uglify())
		.pipe(gulp.dest('css'));
});
