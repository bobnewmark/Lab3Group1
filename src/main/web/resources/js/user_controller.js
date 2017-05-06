'use strict';

App.controller('PhoneController', ['$scope', 'Phone', function($scope, Phone) {
    var self = this;
    self.phone= new Phone();

    self.phones=[];

    self.fetchAllPhones = function(){
        self.phones = Phone.query();
    };

    self.createPhone = function(){
        console.log('Saving phone with id 2222222');
        self.phone.$save(function(){
            console.log('Saving phone with id');
            self.fetchAllPhones();
        });
    };

    self.updatePhone = function(){
        self.phone.$update(function(){
            console.log('Updating phone with id ');
            self.fetchAllPhones();
        });
    };

    self.deletePhone = function(identity){
        var phone = Phone.get({id:identity}, function() {
            phone.$delete(function(){
                console.log('Deleting phone with id ', identity);
                self.fetchAllPhones();
            });
        });
    };

    self.fetchAllPhones();

    self.submit = function() {
        if(self.phone.id==null){
            console.log('Saving New Phone');
            self.createPhone();
        }else{
            console.log('Upddating phone with id ', self.phone.id);
            self.updatePhone();
            console.log('Phone updated with id ', self.phone.id);
        }
        self.reset();
    };

    self.edit = function(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.phones.length; i++){
            if(self.phones[i].id === id) {
                self.phone = angular.copy(self.phones[i]);
                break;
            }
        }
    };

    self.remove = function(id){
        console.log('id to be deleted', id);
        if(self.phone.id === id) {//If it is the one shown on screen, reset screen
            self.reset();
        }
        self.deletePhone(id);
    };


    self.reset = function(){
        self.phone= new Phone();
        $scope.myForm.$setPristine(); //reset Form
    };

}]);