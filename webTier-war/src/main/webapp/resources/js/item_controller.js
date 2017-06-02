'use strict';

angular.module('myApp').controller('ItemController', ['$scope', 'ItemService',  function($scope, ItemService) {
    var self = this;
    self.item = {};
    self.items=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.show = show;
    function getClass() {
        $scope.getClass = function (path) {
            return ($location.path().substr(0, path.length) === path) ? 'active' : '';
        }
    }
    getClass();
    fetchAllItems();

    function fetchAllItems(){
        ItemService.fetchAllItems()
            .then(
                function(d) {
                    self.items = d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function showItem(id) {
        ItemService.showItem(id)
            .then(
                function(d) {
                    self.items = d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function createUser(user){
        UserService.createUser(user)
            .then(
                fetchAllUsers,
                function(errResponse){
                    console.error('Error while creating User');
                }
            );
    }

    function updateUser(user, id){
        UserService.updateUser(user, id)
            .then(
                fetchAllUsers,
                function(errResponse){
                    console.error('Error while updating User');
                }
            );
    }

    function deleteUser(id){
        UserService.deleteUser(id)
            .then(
                fetchAllUsers,
                function(errResponse){
                    console.error('Error while deleting User');
                }
            );
    }

    function show(id) {
        showItem(id);
    }

    function submit() {
        if(self.user.id===null){
            console.log('Saving New User', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].id === id) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.user.id === id) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.user={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);
