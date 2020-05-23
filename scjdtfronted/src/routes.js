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
            </Switch>
        </BrowserRouter>
    );
}