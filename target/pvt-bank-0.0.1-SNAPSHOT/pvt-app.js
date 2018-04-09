var mymodal = angular.module('rlsmodal', ['xeditable']);

mymodal.controller('RlsCtrl', function ($scope,$http,$filter,$q) {
    $scope.showModal = false;
    $scope.showTCModal = false;
    $scope.showCloneModal = false;
    $scope.showCreateModal = false;
    $scope.gridOptions = {};
    $scope.tcResultList = ["PASS", "FAIL","INCOMPLETE"];
    $scope.tcStatusList = ["COMPLETE", "UNKNOWN", "OPEN"];
    $scope.toggleModal = function(){
        $scope.showModal = !$scope.showModal;
    };
    $scope.toggleTCModal = function(){
        $scope.showTCModal = !$scope.showTCModal;
    };
    
    $scope.showNewTSModal = function() {
    	$scope.showCreateModal = !$scope.showCreateModal;
    }
    var url = "service/Release";
    $http.get(url).success( function(response) {
        $scope.release_list = response;
     });
    
    $scope.createRelease = function(release){
      var createRlsURL = "service/CreateRelease";
      $http.post(createRlsURL, release).success(function() {
          console.log("Success : Release Created");
          $scope.showModal = false});
        
      var url = "service/Release";
        $http.get(url).success( function(response) {
            $scope.release_list = response;
            
         });
        $scope.reloadPage = function(){window.location.reload();$scope.release = release;}
    };
    
    $scope.showRelease = function() {
      console.log("Loading "+$scope.selectedRelease.id);
      
      var tsURL = "service/TestSuite/release/"+$scope.selectedRelease.id;
      $http.get(tsURL).success( function(response) {
        console.log("TS URL output : "+response.length);
            $scope.ts_list = response;
            $scope.reloadPage = function(){window.location.reload();$scope.release = release;}
         });
    };
    
    $scope.newRelease = function() {
      $scope.release = null;
      $scope.showModal = true;
    };
    
    $scope.updateTestCases = function(testSuite) {
    	var createTestSuiteURL = "service/TestSuite";
    	$http.post(createTestSuiteURL, testSuite).success(function() {
            console.log("Success : New Test Suite Created");
            });
    };
    
    $scope.createTestSuite = function(newTSRelease, newTestSuiteName) {
    	var createTestSuiteURL = "service/TestSuite/"+newTSRelease.id+"/"+newTestSuiteName;
    	console.log("createTestSuiteURL : "+createTestSuiteURL)
    	$http.post(createTestSuiteURL).success(function() {
    		$scope.showCreateModal = false;
            console.log("Success : New Test Suite Created");
            });
    };
    
    $scope.cloneTestSuite = function(cloneTestSuiteId, cloneRelease, cloneTestSuiteName) {
    	console.log("releaseId : "+cloneRelease.id+";testSuiteId : "+cloneTestSuiteId+";testSuiteName : "+cloneTestSuiteName);
    	var cloneTSURL = "service/TestSuite/fromTS/"+cloneTestSuiteId+"/release/"+cloneRelease.id+"/name/"+cloneTestSuiteName;
    	$http.post(cloneTSURL).success(function() {
            console.log("Success : New Test Suite Created");
            });
    	$scope.showCloneModal = false;
    };
    
    $scope.showTestSuite = function(ts) {
      $scope.ts = ts;
      $scope.showTCModal = true;
      console.log("Test Suited loaded : "+ts.testSuiteId);
      $scope.gridOptions = {enableSorting: true, data: ts.testCaseList};
    };
    
    $scope.changeTCResult = function(testCase, tcResultItem) {
    	testCase.result = tcResultItem;
    };
    
    $scope.changeTSResult = function(testCase, tcStatusItem) {
    	testCase.status = tcStatusItem;
    };
    
    $scope.startCloneModal = function(testSuite) {
    	$scope.cloneTestSuiteId = testSuite.testSuiteId;
    	$scope.showCloneModal = true;
    };
    
    $scope.showStatus = function(testCase) {
        var selected = [];
        console.log("testCase.status "+testCase.status);
        if(testCase.status) {
          selected = $filter('filter')($scope.tcStatusList, {value: testCase.status});
        }
        return selected ? selected[0] : 'Not set';
      };
      
    $scope.getPassCount = function(testCaseList) {
    	passCount = $filter('filter')(testCaseList, {value: "PASS"});
    };
      
	$scope.showResult = function(testCase) {
	    var selected = [];
	    if(testCase.result) {
	      selected = $filter('filter')($scope.tcResultList, {value: testCase.result});
	    }
	    return selected ? selected[0] : 'Not set';
	  };
	  
	$scope.deleteTC = function(testCaseId) {
		console.log("testCaseID : "+testCaseId);
    	var deleteTCURL = "service/TestCase/"+testCaseId;
    	$http.delete(deleteTCURL).success(function() {
            console.log("Success : Test Case Deleted");
            });
	  };
	  
	$scope.deleteTestSuite = function(testSuiteId) {
		console.log("testSuiteID : "+testSuiteId);
    	var deleteTSURL = "service/TestSuite/"+testSuiteId;
    	$http.delete(deleteTSURL).success(function() {
            console.log("Success : Test Suite Deleted");
            });
	  };

	  $scope.addTC = function() {
		    $scope.ts.testCaseList.push({
		      id: '',
		      status: null,
		      result: null
		    });
		  };
    
  });

mymodal.directive('modal', function () {
    return {
      template: '<div class="modal fade">' + 
          '<div class="modal-dialog">' + 
            '<div class="modal-content">' + 
              '<div class="modal-header">' + 
                '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>' + 
                '<h4 class="modal-title">{{ title }}</h4>' + 
              '</div>' + 
              '<div class="modal-body" ng-transclude></div>' + 
            '</div>' + 
          '</div>' + 
        '</div>',
      restrict: 'E',
      transclude: true,
      replace:true,
      scope:true,
      link: function postLink(scope, element, attrs) {
        scope.title = attrs.title;

        scope.$watch(attrs.visible, function(value){
          if(value == true)
            $(element).modal('show');
          else
            $(element).modal('hide');
        });

        $(element).on('shown.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = true;
          });
        });

        $(element).on('hidden.bs.modal', function(){
          scope.$apply(function(){
            scope.$parent[attrs.visible] = false;
          });
        });
      }
    };
  });
