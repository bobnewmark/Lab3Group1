<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="editModal" class="modal fade" phoneModal>
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
                        <input type="hidden" ng-model="ctrl.item.id"/>
                        <div class="row" ng-repeat="par in ctrl.item.parameters">
                            <div class="form-group col-md-12" ng-if="par.attribute.hidden!=true">
                                <label class="col-md-2 control-lable" for="{{par.attribute.name}}">{{par.attribute.name}}</label>
                                <div ng-switch="par.attribute.attach">
                                    <div class="col-md-7" ng-switch-when="true">
                                        <input type="file" file-model="ctrl.files[par.attribute.name]">
                                    </div>
                                    <div class="col-md-7" ng-switch-default>
                                        <input type="text" ng-model="par.value"
                                               class="{{par.attribute.name}} form-control input-sm"
                                               placeholder="Enter {{par.attribute.name}}" required/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Футер модального окна -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <input type="submit" value="{{!ctrl.item.id ? 'Добавить' : 'Редактировать'}}"
                           class="btn btn-primary" ng-disabled="myForm.$invalid"/>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="addModal" class="modal fade" phoneModal>
    <div class="modal-dialog">
        <div class="modal-content">
            <form ng-submit="ctrl.submit()" name="addForm" class="form-horizontal">
                <!-- Заголовок модального окна -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">Добавление товара</h4>
                </div>
                <!-- Основное содержимое модального окна -->
                <div class="modal-body">
                    <div class="formcontainer">
                        <input type="hidden" ng-model="ctrl.item.id"/>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label class="col-md-6 control-lable" for="selectType">Выберите тип</label>
                                <select id="selectType" ng-options="type as type.name for type in ctrl.types"
                                        ng-model="selectedItem" ng-change="ctrl.update()" required></select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6" ng-show="ctrl.item.objectType.name=='Phone'">
                                <label class="col-md-6 control-lable" for="selectBrand">Выберите бренд</label>
                                <select id="selectBrand" ng-options="brand as brand.name for brand in ctrl.brands"
                                        ng-model="ctrl.item.parent" required></select>
                            </div>
                        </div>
                        <div class="row" ng-repeat="p in ctrl.item.parameters">
                            <div class="form-group col-md-12" ng-if="p.attribute.hidden!=true">
                                <label class="col-md-2 control-lable"
                                       for="{{p.attribute.name}}">{{p.attribute.name}}</label>
                                <div ng-switch="p.attribute.attach">
                                    <div class="col-md-7" ng-switch-when="true">
                                        <input type="file" file-model="ctrl.files[p.attribute.name]">
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
                    <input type="submit" value="{{!ctrl.item.id ? 'Добавить' : 'Редактировать'}}"
                           class="btn btn-primary" ng-disabled="addForm.$invalid"/>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="typesModal" class="modal fade" typeModal>
    <div class="modal-dialog">
        <div class="modal-content">
            <form ng-submit="ctrl.submit()" name="typeForm" class="form-horizontal">
                <!-- Заголовок модального окна -->
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">изменение/добавление типов</h4>
                </div>
                <!-- Основное содержимое модального окна -->
                <div class="modal-body">
                    <div class="formcontainer">
                        <input type="hidden" ng-model="ctrl.item.id"/>
                        <div class="row">
                                <label class="col-md-6 control-lable" for="selectType">Выберите родителя</label>
                                <select id="selectTypes" ng-options="type as type.name for type in ctrl.types"
                                        ng-model="ctrl.type.parent" required></select>
                                <div class="form-group col-md-12">
                                    <label class="col-md-2 control-lable">Имя типа</label>
                                    <div class="col-md-4">
                                        <input type="text" ng-model="ctrl.type.name"  style="width: 70px"
                                               class="{{ctrl.type.name}} form-control input-sm"
                                               placeholder="Enter {{ctrl.type.name}}" required/>
                                    </div>
                                </div>
                                <div class="form-group col-md-12" ng-repeat="a in ctrl.type.attributes">
                                    <label class="col-md-3 control-lable">имя атрибута</label>
                                    <div class="col-md-6">
                                        <input type="text" ng-model="a.name" style="width: 70px"
                                               class="{{a.name}} form-control input-sm"
                                               placeholder="Enter {{a.name}}" required/>
                                    </div>
                                    <label class="col-md-4 control-lable">уникальность</label>
                                    <div class="col-md-1">
                                        <input type="checkbox" ng-model="a.unique"/>
                                    </div>
                                    <label class="col-md-4 control-lable">скрытый</label>
                                    <div class="col-md-1">
                                        <input type="checkbox" ng-model="a.hidden"/>
                                    </div>
                                    <label class="col-md-4 control-lable">файл</label>
                                    <div class="col-md-1">
                                        <input type="checkbox" ng-model="a.attach"/>
                                    </div>
                                </div>
                                <div style="color: royalblue" ng-click="ctrl.addAttr()">Добавить атрибут<i class="fa fa-plus"></i> </div>
                            </div>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        <input type="submit" value="Добавить" class="btn btn-primary" />
                    </div>
                </div>
                <!-- Футер модального окна -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <input type="submit" value="{{!ctrl.item.id ? 'Добавить' : 'Редактировать'}}"
                           class="btn btn-primary"
                           ng-disabled="addForm.$invalid"/>
                </div>
            </form>
        </div>
    </div>
</div>