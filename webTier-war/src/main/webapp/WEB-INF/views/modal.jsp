<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--modal window for edit object--%>

<div id="editModal" class="modal fade" phoneModal>
    <div class="modal-dialog">
        <div class="modal-content">
            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" ng-click="ctrl.reset()" aria-hidden="true">×</button>
                    <h4 class="modal-title">Изменение объекта</h4>
                </div>

                <div class="modal-body">
                    <div class="formcontainer">
                        <input type="hidden" ng-model="ctrl.item.id"/>
                        <div class="row" ng-repeat="par in ctrl.item.parameters">
                            <div class="form-group col-md-12" ng-if="par.attribute.hidden!=true">
                                <label class="col-md-2 control-lable">{{par.attribute.name}}</label>
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

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" ng-click="ctrl.reset()" data-dismiss="modal">Закрыть</button>
                    <input type="submit" value="{{!ctrl.item.id ? 'Добавить' : 'Редактировать'}}"
                           class="btn btn-primary" ng-disabled="myForm.$invalid"/>
                </div>
            </form>
        </div>
    </div>
</div>

<%--modal window for create object--%>

<div id="addModal" class="modal fade" phoneModal>
    <div class="modal-dialog">
        <div class="modal-content">
            <form ng-submit="ctrl.submit()" name="addForm" class="form-horizontal">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"  ng-click="ctrl.resetAdd()" aria-hidden="true">×</button>
                    <h4 class="modal-title">Добавление объектов</h4>
                </div>
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
                                        ng-model="ctrl.item.parent"></select>
                            </div>
                        </div>
                        <div class="row" ng-repeat="p in ctrl.item.parameters">
                            <div class="form-group col-md-12" ng-if="p.attribute.hidden!=true">
                                <label class="col-md-2 control-lable">{{p.attribute.name}}</label>
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
                        <div class="row" ng-repeat="p in ctrl.item.references">
                            <div class="form-group col-md-12" ng-if="p.attribute.hidden!=true">
                                <label class="col-md-2 control-lable">{{p.attribute.name}}</label>
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
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"  ng-click="ctrl.resetAdd()" data-dismiss="modal">Закрыть</button>
                    <input type="submit" value="Добавить"
                           class="btn btn-primary" ng-disabled="addForm.$invalid"/>
                </div>
            </form>
        </div>
    </div>
</div>

<%--modal window for create object types--%>

<%--modal window for edit object types--%>

<div id="editTypesModal" class="modal fade" typeModal>
    <div class="modal-dialog">
        <div class="modal-content">
            <form ng-submit="ctrl.submitType()" name="typeForm" class="form-horizontal">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"  ng-click="ctrl.resetType()" aria-hidden="true">×</button>
                    <h4 class="modal-title">Редактирование типа</h4>
                </div>

                <div class="modal-body">

                    <div class="form-group col-md-12">
                        <div class="col-md-3" ng-repeat="type in ctrl.types">
                            <a href="#" ng-click="ctrl.selectType(type.objectType)">
                                <i class="{{type.objectType.icon}}"></i> {{type.objectType.name}}
                            </a>
                        </div>
                        <div class="col-md-3">
                            <a href="#" ng-click="ctrl.resetType()">
                                <i class="fa fa-plus"></i> add type
                            </a>
                        </div>
                    </div>
                    <div class="formcontainer">
                        <input type="hidden" ng-model="ctrl.item.id"/>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <div class="form-group col-md-12">
                                    <label class="col-md-5 control-lable">Имя типа</label>
                                    <input type="text" ng-model="ctrl.type.name" style="width: 120px"
                                           class="{{ctrl.type.name}} form-control input-sm"
                                           placeholder="Enter {{ctrl.type.name}}" required/>
                                </div>
                                <div class="form-group col-md-12">
                                    <label class="col-md-5 control-lable" for="selectType">Выберите родителя</label>
                                    <select id="selType" style="width: 120px" ng-model="selectedItem"
                                            ng-options="type.objectType.id as type.name for type in ctrl.types"
                                            ng-change="ctrl.setParent()"></select>
                                </div>
                                <div class="form-group col-md-12">
                                    <label class="col-md-5 control-lable" for="selectType">Картинка</label>
                                    <a href="#" ng-click="ctrl.reqIcon()">
                                        <i style="font-size: 24px" class="{{ctrl.type.icon}}"></i>
                                    </a>
                                </div>
                            </div>
                            <div class="form-group col-md-4" ng-if="ctrl.hide == false">
                                <jsp:include page="/WEB-INF/views/icons.jsp" flush="true"></jsp:include>
                            </div>
                            <div style="border-bottom: 1px solid #e5e5e5; text-align: center"
                                 class="form-group col-md-12">Атрибуты
                            </div>
                            <div class="form-group col-md-11" ng-repeat="a in ctrl.type.attributes">
                                <label class="col-md-1 control-lable">имя</label>
                                <div class="col-md-3">
                                    <input type="text" ng-model="a.name" style="width: 120px"
                                           class="{{a.name}} form-control input-sm"
                                           placeholder="Enter {{a.name}}" required/>
                                </div>
                                <label class="col-md-2 control-lable" style="font-weight: 100">уникальный</label>
                                <div class="col-md-1">
                                    <input type="checkbox" ng-model="a.unique"/>
                                </div>
                                <label class="col-md-2 control-lable" style="font-weight: 100">скрытый</label>
                                <div class="col-md-1">
                                    <input type="checkbox" ng-model="a.hidden"/>
                                </div>
                                <label class="col-md-1 control-lable" style="font-weight: 100">файл</label>
                                <div class="col-md-1">
                                    <input type="checkbox" ng-model="a.attach"/>
                                </div>
                            </div>
                            <div class="col-md-1" style="color: green" ng-click="ctrl.addAttr()"><i
                                    class="fa fa-plus"></i></div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default"  ng-click="ctrl.resetType()"  data-dismiss="modal">Закрыть</button>
                    <input type="submit" value="Добавить"
                           class="btn btn-primary"
                           ng-disabled="typeForm.$invalid"/>
                </div>
            </form>
        </div>
    </div>
</div>