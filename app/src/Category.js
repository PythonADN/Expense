import React, {Component} from "react";
import AppNav from "./AppNav";


class Category extends Component {
    state = {
        categories: [],
        isLoading: true
    };

    componentDidMount = async () => {
        const response = await fetch('category');
        const body = await response.json();

        this.setState({categories: body, isLoading: false})
    };

    render() {
        const {categories, isLoading} = this.state;

        if (isLoading) {
            return (<div>Загрузка...</div>)
        }

        return (
            <div>
                <AppNav />
                <h2>Categories</h2>
                {categories.map(category => <div id={category.id}>{category.name}</div>)}
            </div>
        );
    }
}

export default Category;