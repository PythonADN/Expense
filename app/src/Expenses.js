import React, { Component } from 'react';
import AppNav from './AppNav';
import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import './App.css';
import {Table, Container, Button, FormGroup, Form, Label, Input} from 'reactstrap';
import {Link} from 'react-router-dom';
import Moment from "react-moment";

class Expenses extends Component {
    state = {
        date: new Date(),
        isLoading: true,
        categories: [],
        expenses: [],
        item: this.emptyItem,
    };

    emptyItem = {
        id: '104',
        expenseDate: new Date(),
        description: 'default',
        location: '',
        category: {id:1 , name:'Travel'}
    };

    handleSubmit = async (event) => {
        const { item } = this.state;
        console.log(event);
        await fetch(`expenses`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        // обнулить форму после отправки
        event.preventDefault();
        this.props.history.push("/expenses");
    };

    handleChange = (event) => {
        const target= event.target;
        const value= target.value;
        const name = target.name;
        let item={...this.state.item};
        if (name === 'category') {
            item[name] = { id: '4', name: value };
        } else {
            item[name] = value;
        }
        this.setState({item});
        console.log(JSON.stringify(item));
    };

    handleDateChange = (date) => {
        let item={...this.state.item};
        item.expenseDate= date;
        this.setState({item});
    };

    remove = async (id) => {
        await fetch(`expenses/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        }).then(() => {
            let updatedExpenses = [...this.state.expenses].filter(el => el.id !== id);
            this.setState({ expenses: updatedExpenses });
        })
    };

    // хз зачем создан конструктор
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            categories: [],
            expenses: [],
            date: new Date(),
            item: this.emptyItem,
        }
    }

    componentDidMount = async () => {
        const responseCategories = await fetch('category');
        const bodyCategories = await responseCategories.json();
        this.setState({ categories: bodyCategories, isLoading: false })

        const responseExpenses = await fetch('expenses');
        const bodyExpenses = await responseExpenses.json();
        this.setState({ expenses: bodyExpenses, isLoading: false })
    };


    render() {
        const { categories, expenses, isLoading } = this.state;
        const title = <h3>Добавить расходы</h3>;

        if (isLoading) {
            return (<div>Загрузка...</div>)
        }

        return (
            <div>
                <AppNav />
                <Container>
                    {title}
                    <Form onSubmit={this.handleSubmit}>
                        <FormGroup>
                            <Label for="description">Title</Label>
                            <Input type="text" name="description"  id="description" onChange={this.handleChange} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="category">Category</Label>
                            <select name="category" onChange={this.handleChange}>
                                {categories.map(category => <option id={category.id}>{category.name}</option>)}
                            </select>
                        </FormGroup>
                        <FormGroup>
                            <Label for="city">Expense Date</Label>
                            <DatePicker selected={this.state.item.expenseDate} onChange={this.handleDateChange} />
                        </FormGroup>
                        <FormGroup>
                            <Label for="location">Location</Label>
                            <Input type="text" name="location"  id="location" onChange={this.handleChange} />
                        </FormGroup>
                        <FormGroup>
                            <Button color="primary" type="submit">Save</Button>
                            <Button color="secondary" tag={Link} to="/categories">Cancel</Button>
                        </FormGroup>
                    </Form>
                </Container>
                <Container>
                    <h3>Расходы</h3>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th width="30%">Description</th>
                                <th width="15%">Location</th>   
                                <th width="30%">Date</th>
                                <th>Category</th>
                                <th width="10%">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {expenses.map(expens =>
                                <tr>
                                    <td>{expens.description}</td>
                                    <td>{expens.location}</td>
                                    <td><Moment date={expens.expenseDate} format="YYYY/MM/DD" /></td>
                                    <td>{expens.category.name}</td>
                                    <td><Button size="sm" color="danger" onClick={() => this.remove(expens.id)}>Удалить</Button></td>
                                </tr>
                            )}
                        </tbody>
                    </Table>
                </Container>
            </div>
        )
    }
}

export default Expenses;