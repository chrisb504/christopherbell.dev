var gulp = require('gulp');
var uglify = require('gulp-uglify');
var csso = require('gulp-csso');
var rename = require('gulp-rename');
var concat = require('gulp-concat');
var connect = require('gulp-connect');
var sass = require('gulp-sass');
var gutil = require('gulp-util');

//var gulpLoadPlugins = require('gulp-load-plugins');
//var plugins = gulpLoadPlugins();

// gulp.task('connect', function() {
//   connect.server({
//     root: '.',
//     livereload: true
//   })
// });

gulp.task('log', function() {
    gutil.log('== My Log Task ==')
  });

gulp.task('sass', function() {
    return gulp.src('./public/css/main.scss')
        .pipe(sass({style: 'expanded'}))
        .on('error', gutil.log)
        // .pipe(gulp.dest('public/css'))
        .pipe(csso())
        .pipe(rename({suffix:'.min'}))
        .pipe(gulp.dest('public/css'));
});

gulp.task('js', function() {
    return gulp.src([
        "apps/blog/public/js/blog.js",
        "apps/header/js/header.js",
        "apps/footer/js/footer.js"])
        .pipe(concat("main.js"))
        .pipe(gulp.dest('public/js'))
        // .pipe(uglify())
        .pipe(rename({suffix:'.min'}))
        .pipe(gulp.dest('public/js/'));
});


// gulp.task('watch', function() {
//     gulp.watch('css/**/*.css', ['styles']); 
// });


//  Watch Tasks
//gulp.task('watch', function() {
//    gulp.watch('js/**/.js', ['scripts'] )
//});

//gulp.task('default', ['sass', 'scripts']);