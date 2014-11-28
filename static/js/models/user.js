define([
	'backbone'
	],
	function(
		Backbone
	)
{
    var Model = Backbone.Model.extend({

        defaults: function () {
//            TODO
            this.set({
                name: "",
                gameCount: 0,
                gameLose: 0,
                gameWin: 0,
                isLogined: false
            });
        },

        isLogined: function () {
            return this.get('isLogined');
        },

        login: function () {
//            TODO
            this.set({
                name: "Gex",
                gameCount: 5,
                gameLose: 1,
                gameWin: 3,
                isLogined: true
            });
            console.log(this.toJSON());
        },

        logout: function () {
//            TODO
            this.defaults();
            console.log(this.toJSON());
        }
    });

    return new Model();
});