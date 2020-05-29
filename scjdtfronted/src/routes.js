import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom'

import Login from './pages/Login';
import Dashboard from './pages/Dashboard'

import UserCreate from './pages/User/Usercreate';
import Userlist from './pages/User/Userlist'
import Useredit from './pages/User/Useredit'

import Categorycreate from './pages/Category/Categorycreate'
import Categoryedit from './pages/Category/Categoryedit'
import Categorylist from './pages/Category/Categorylist'

import Functioncreate from './pages/Func/Functioncreate'
import Functionedit from './pages/Func/Functionedit'
import Functionlist from './pages/Func/Functionlist'

import Localcreate from './pages/Localwork/Localcreate'
import Locallist from './pages/Localwork/Locallist'
import Localedit from './pages/Localwork/Localedit'

import Collaboratorcreate from './pages/Collaborator/Collaboratorcreate'
import Collaboratoredit from './pages/Collaborator/Collaboratoredit'
import Collaboratorlist from './pages/Collaborator/Collaboratorlist'

export default function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Login}/>
                <Route path="/dashboard" component={Dashboard}/>

                <Route path="/user-create" component={UserCreate}/>
                <Route path="/user-list" component={Userlist}/>
                <Route path="/user-edit/:id" component={Useredit}/>
                
                <Route path="/category" component={Categorycreate}/>
                <Route path="/category-edit/:id" component={Categoryedit}/>
                <Route path="/category-list/" component={Categorylist}/>

                <Route path="/function-create" component={Functioncreate}/>
                <Route path="/function-edit/:id" component={Functionedit}/>
                <Route path="/function-list" component={Functionlist}/>

                <Route path="/local-create" component={Localcreate}/>
                <Route path="/local-list" component={Locallist}/>
                <Route path="/local-edit/:id" component={Localedit}/>

                <Route path="/collaborator-create" component={Collaboratorcreate}/>
                <Route path="/collaborator-edit/:id" component={Collaboratoredit}/>
                <Route path="/collaborator-list" component={Collaboratorlist}/>
            </Switch>
        </BrowserRouter>
    );
}