import React, {Component} from "react";
import {Route, BrowserRouter, Switch} from "react-router-dom";
import Home from "./Home";
import Category from "./Category";
import Expenses from "./Expenses";

/**
 * Маршрутизатор
 */
class App extends Component {
    render() {
        return (
            <BrowserRouter>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/categories' exact={true} component={Category}/>
                    <Route path='/expenses' exact={true} component={Expenses}/>
                </Switch>
            </BrowserRouter>
        );
    }
}

export default App;