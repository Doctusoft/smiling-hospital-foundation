'use strict';

var gulp = require('gulp');
var browserSync = require('browser-sync');
var mkdirp = require('mkdirp');

var $ = require('gulp-load-plugins')();

var tsProject = ts.createProject({
  declarationFiles: true,
  target: 'ES6',
  sortOutput: true
});

module.exports = function(options) {
  gulp.task('scripts', ['tsd:install'], function () {
    mkdirp.sync(options.tmp);

    return gulp.src(options.src + '/{app,components}/**/*.ts')
      .pipe($.sourcemaps.init())
      .pipe($.tslint())
      .pipe($.tslint.report('prose', { emitError: false }))
      .pipe($.typescript(tsProject)
	  .pipe(concat('/app/smilingHospitalUi.js'))
	  .on('error', options.errorHandler('TypeScript'))
      .pipe($.sourcemaps.write())
      .pipe($.toJson({filename: options.tmp + '/sortOutput.json', relative:true}))
      .pipe(gulp.dest(options.tmp + '/serve/'))
      .pipe(browserSync.reload({ stream: true }))
      .pipe($.size());
  });
};
