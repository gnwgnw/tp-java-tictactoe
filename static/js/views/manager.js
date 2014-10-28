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
            this.listenTo(main, 'show', this.lets_show);
            this.listenTo(game, 'show', this.lets_show);
            this.listenTo(login, 'show', this.lets_show);
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