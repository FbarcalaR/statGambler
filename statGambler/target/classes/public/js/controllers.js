angular.module('app.controllers', []).controller('UserListController', function($scope, $state, popupService, $window, User) {
  $scope.users = User.query(); //fetch all shipwrecks. Issues a GET to /api/vi/shipwrecks

  $scope.deleteUser = function(user) { // Delete a Shipwreck. Issues a DELETE to /api/v1/shipwrecks/:id
    if (popupService.showPopup('Really delete this?')) {
      user.$delete(function() {
        $scope.users = User.query(); 
        $state.go('users');
      });
    }
  };
}).controller('UserViewController', function($scope, $stateParams, User) {
  $scope.user = User.get({ id: $stateParams.id }); //Get a single shipwreck.Issues a GET to /api/v1/shipwrecks/:id
}).controller('UserCreateController', function($scope, $state, $stateParams, User) {
  $scope.user = new User();  //create new shipwreck instance. Properties will be set via ng-model on UI

  $scope.addUser = function() { //create a new shipwreck. Issues a POST to /api/v1/shipwrecks
    $scope.user.$save(function() {
      $state.go('users'); // on success go back to the list i.e. shipwrecks state.
    });
  };
}).controller('UserEditController', function($scope, $state, $stateParams, User) {
  $scope.updateUser = function() { //Update the edited shipwreck. Issues a PUT to /api/v1/shipwrecks/:id
    $scope.user.$update(function() {
      $state.go('users'); // on success go back to the list i.e. shipwrecks state.
    });
  };

  $scope.loadUser = function() { //Issues a GET request to /api/v1/shipwrecks/:id to get a shipwreck to update
    $scope.user = User.get({ id: $stateParams.id });
  };

  $scope.loadUser(); // Load a shipwreck which can be edited on UI
});
