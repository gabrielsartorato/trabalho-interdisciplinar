import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom'

import Login from './pages/Login';
import UserCreate from './pages/User';
import Dashboard from './pages/Dashboard'
import Category from './pages/Category'
import Userlist from './pages/Userlist'
import Useredit from './pages/Useredit'

export default function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={Login}/>
                <Route path="/user-create" component={UserCreate}/>
                <Route path="/user-list" component={Userlist}/>
                <Route path="/user-edit/:id" component={Useredit}/>
                <Route path="/dashboard" component={Dashboard}/>
                <Route path="/category" component={Category}/>
            </Switch>
        </BrowserRouter>
    );
}