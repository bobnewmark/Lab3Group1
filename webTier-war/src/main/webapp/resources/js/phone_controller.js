'use strict';

angular.module('myApp').controller('PhoneController', ['$scope', 'PhoneService', function($scope, UserService) {
    var self = this;
    self.phone={id:null,phonename:'',address:'',email:''};
    self.phones=[];

    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;


    fetchAllUsers();

    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
                function(d) {
                    self.phones = d;
                },
                function(errResponse){
                    console.error('Error while fetching Users');
                }
            );
    }

    function createUser(phone){
        UserService.createUser(phone)
            .then(
                fetchAllUsers,
                function(errResponse){
                    console.error('Error while creating User');
                }
            );
    }

    function updateUser(phone, id){
        UserService.updateUser(phone, id)
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

    function submit() {
        if(self.phone.id===null){
            console.log('Saving New User', self.phone);
            createUser(self.phone);
        }else{
            updateUser(self.phone, self.phone.id);
            console.log('User updated with id ', self.phone.id);
        }
        reset();
    }

    function edit(id){
        console.log('id to be edited', id);
        for(var i = 0; i < self.phones.length; i++){
            if(self.phones[i].id === id) {
                self.phone = angular.copy(self.phones[i]);
                break;
            }
        }
    }

    function remove(id){
        console.log('id to be deleted', id);
        if(self.phone.id === id) {//clean form if the phone to be deleted is shown there.
            reset();
        }
        deleteUser(id);
    }


    function reset(){
        self.phone={id:null,phonename:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    }

}]);