define([
    'backbone',
    'views/main',
    'views/game',
    'views/login',
    'views/registration',
    'views/scoreboard'
    ],
    function(
        Backbone,
        main,
        game,
        login,
        registration,
        scoreboard
    )
{
    var views = [main, game, login, registration, scoreboard];
    
    var Manager = Backbone.View.extend({
    
        initialize: function () {
            this.listenTo(main, 'show', this.lets_show);
            this.listenTo(game, 'show', this.lets_show);
            this.listenTo(login, 'show', this.lets_show);
            this.listenTo(registration, 'show', this.lets_show);
            this.listenTo(scoreboard, 'show', this.lets_show);
            
        },
        lets_show:function (cur_screen) {                        
            _.each(views, function(element){
                if (element != cur_screen)
                    element.hide();
            });            
        }            
    });
    
    return new Manager();
});