import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class UserCreateComponent extends React.Component {
    emptyEntity = {
        dni:'',
        name:'',
        telephones:[]
    };

    constructor(props){
        super(props);
        this.state = {
            item:this.emptyEntity
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const user = await (await fetch(`/api/user/${this.props.match.params.id}`)).json();
            this.setState({item: user});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
    
        await fetch('/api/user' + (item.id ? '/' + item.id : ''), {
          method: (item.id) ? 'PUT' : 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(item),
        });
        this.props.history.push('/users');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Editar Usuario' : 'Agregar Usuario'}</h2>;
    
        return <div className="container m-5 mx-auto">
          {/* <AppNavbar/> */}
          <Container>
            {title}
            <Form onSubmit={this.handleSubmit}>
              <FormGroup>
                <Label for="dni">Cédula</Label>
                <Input type="text" name="dni" id="dni" value={item.dni || ''}
                       onChange={this.handleChange} autoComplete="dni"/>
              </FormGroup>
              <FormGroup>
                <Label for="name">Nombre</Label>
                <Input type="text" name="name" id="name" value={item.name || ''}
                       onChange={this.handleChange} autoComplete="name"/>
              </FormGroup>
              
              <FormGroup>
                <Button color="primary" type="submit">Guardar</Button>{' '}
                <Button color="secondary" tag={Link} to="/users">Cancelar</Button>
              </FormGroup>
            </Form>
          </Container>
        </div>
      }
}

export default UserCreateComponent;