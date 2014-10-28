define([
    'backbone',
    'views/main',
    'views/game',
    'views/login',
    'views/scoreboard'
    ],
    function(
        Backbone,
        main,
        game,
        login,
        scoreboard
    )
{
    var views = [main, game, login, scoreboard];
    
    var Manager = Backbone.View.extend({
    
        initialize: function () {
            this.listenTo(main, 'show', this.lets_show_main);
            this.listenTo(game, 'show', this.lets_show_game);
            this.listenTo(login, 'show', this.lets_show_login);
            this.listenTo(scoreboard, 'show', this.lets_show_scoreboard);
            
        },
        lets_show_main:function () {                        
            _.each(views, function(element){
                if (element != main)
                    element.hide();
            });            
        },
        lets_show_login:function () {                        
            _.each(views, function(element){
                if (element != login)
                    element.hide();
            });            
        },
        lets_show_scoreboard:function () {                        
            _.each(views, function(element){
                if (element != scoreboard)
                    element.hide();
            });            
        },
        lets_show_game:function () {
            _.each(views, function(element){
                if (element != game)
                    element.hide();
            });            
        }        
    });
    
    return new Manager();
});