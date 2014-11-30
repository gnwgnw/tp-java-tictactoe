module.exports = function (grunt) {

    grunt.initConfig({
        shell: {
            options: {
                stdout: true,
                stderr: true
            },
            server: {
                command: 'java -cp TicTacToe.jar main.Main'
            }
        },
        fest: {
            templates: {
                files: [{
                    expand: true,
                    cwd: 'templates/frontend',
                    src: '*.xml',
                    dest: 'static/js/tmpl'
                }],
                options: {
                    template: function (data) {
                        return grunt.template.process(
                            'define(function () { return <%= contents %> ; });',
                            {data: data}
                        );
                    }
                }
            }
        },
        sass: {
            dist: {
                options: {
                    style: 'compressed'
                },
                files: {
                    'static/css/main.css': 'static/css/scss/main.scss'
                }
            }
        },
        watch: {
            fest: {
                files: ['templates/frontend/*.xml'],
                tasks: ['fest'],
                options: {
                    interrupt: true,
                    atBegin: true
                }
            },
            server: {
                files: [
                    'static/js/**/*.js',
                    'static/css/**/*.css'
                ],
                options: {
                    livereload: true
                }
            },
            css: {
                files: ['static/css/scss/*.scss'],
                tasks: ['sass'],
                options: {
                    spawn: false,
                }
            }
        },
        concurrent: {
            target: ['watch', 'shell'],
            options: {
                logConcurrentOutput: true
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-concurrent');
    grunt.loadNpmTasks('grunt-shell');
    grunt.loadNpmTasks('grunt-fest');
    grunt.loadNpmTasks('grunt-contrib-sass');

    grunt.registerTask('default', ['concurrent']);

};