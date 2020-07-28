/* eslint-disable import/no-self-import */
const gulp = require('gulp');
const csso = require('gulp-csso');
const rename = require('gulp-rename');
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
