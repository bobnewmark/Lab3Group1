<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="editModal" class="modal fade" phoneModal >
    <div class="modal-dialog">
        <div class="modal-content">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <!-- Заголовок модального окна -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Изменение товара</h4>
                </div>
                <!-- Основное содержимое модального окна -->
                <div class="modal-body">
                    <div class="formcontainer">
                        <input type="hidden" ng-model="ctrl.item.id" />
                        <div class="row" ng-repeat="p in ctrl.item.parameters">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable" for="{{p.attribute.name}}">{{p.attribute.name}}</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="p.value" id="{{p.attribute.name}}" class="{{p.attribute.name}} form-control input-sm" placeholder="Enter {{p.attribute.name}}" required/>
                                    <input type="hidden" ng-model="p.object.id" value="{{ctrl.item.id}}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Футер модального окна -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <input type="submit" value="{{!ctrl.item.id ? 'Добавить' : 'Редактировать'}}"class="btn btn-primary" ng-disabled="myForm.$invalid" />
                </div>
            </form>
        </div>
    </div>
</div>

<div id="addModal" class="modal fade" phoneModal >
    <div class="modal-dialog">
        <div class="modal-content">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                <!-- Заголовок модального окна -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Добавление товара</h4>
                </div>
                <!-- Основное содержимое модального окна -->
                <div class="modal-body">
                    <div class="formcontainer">
                        <input type="hidden" ng-model="ctrl.item.id" />
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label class="col-md-6 control-lable" for="selectType">Выберите тип</label>
                                <select id="selectType" ng-options="type as type.name for type in ctrl.types"
                                        ng-model="selectedItem" ng-change="ctrl.update()" required></select>
                            </div>
                        </div>
                        <div class="row" ng-repeat="p in ctrl.item.parameters">
                            <div class="form-group col-md-12" ng-if="p.attribute.hidden!=true">
                                <label class="col-md-2 control-lable" for="{{p.attribute.name}}">{{p.attribute.name}}</label>
                                <div ng-switch = "p.attribute.attach">
                                    <div class="col-md-7" ng-switch-when="true">
                                        <input type="file" ng-model="p.value"
                                               class="{{p.attribute.name}} form-control input-sm"
                                               placeholder="Enter {{p.attribute.name}}" required/>
                                    </div>
                                    <div class="col-md-7" ng-switch-default>
                                        <input type="text" ng-model="p.value"
                                               class="{{p.attribute.name}} form-control input-sm"
                                               placeholder="Enter {{p.attribute.name}}" required/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Футер модального окна -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <input type="submit" value="{{!ctrl.item.id ? 'Добавить' : 'Редактировать'}}"class="btn btn-primary" ng-disabled="myForm.$invalid" />
                </div>
            </form>
        </div>
    </div>
</div>