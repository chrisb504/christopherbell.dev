const gulp = require('gulp');
const uglify = require('gulp-uglify');
const csso = require('gulp-csso');
const rename = require('gulp-rename');
const concat = require('gulp-concat');
const connect = require('gulp-connect');
const sass = require('gulp-sass');
const gutil = require('gulp-util');

gulp.task('log', () => {
    gutil.log('== My Log Task ==');
});

gulp.task('sass', () => {
    return gulp.src('./src/main/resources/static/css/main.scss')
        .pipe(sass({
            style: 'expanded'
        }))
        .on('error', gutil.log)
        // .pipe(gulp.dest('public/css'))
        .pipe(csso())
        .pipe(rename({
            suffix: '.min'
        }))
        .pipe(gulp.dest('./src/main/resources/static/css/'));
});

gulp.task('js', () => {
    return gulp.src([
        'apps/blog/public/js/blog.js',
        'apps/header/js/header.js',
        'apps/footer/js/footer.js'
    ])
        .pipe(concat('main.js'))
        .pipe(gulp.dest('public/js'))
        // .pipe(uglify())
        .pipe(rename({
            suffix: '.min'
        }))
        .pipe(gulp.dest('public/js/'));
});
