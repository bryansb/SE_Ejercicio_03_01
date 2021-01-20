import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class telephoneCreateComponent extends React.Component {
    emptyEntity = {
        number:'',
    };

    constructor(props){
        super(props);
        this.state = {
            item:this.emptyEntity,
            userId:0       
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        const userId = this.props.match.params.userId;
        this.setState({userId: userId});

        if (this.props.match.params.id !== 'add') {
            const telephone = await (await fetch(`/api/user/telephone/${this.state.userId}/${this.props.match.params.id}`)).json();
            this.setState({item: telephone});
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

        await fetch('/api/user/telephone/' + this.state.userId + (item.id ? '/' + item.id : ''), {
          method: (item.id) ? 'PUT' : 'POST',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(item),
        });
        this.props.history.push('/telephones/' + this.state.userId);
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Editar Teléfono' : 'Agregar Teléfono'}</h2>;
    
        return <div className="container m-5 mx-auto">
          <Container>
            {title}
            <Form onSubmit={this.handleSubmit}>
              <FormGroup>
                <Label for="number">Número</Label>
                <Input type="text" name="number" id="number" value={item.number || ''}
                       onChange={this.handleChange} autoComplete="number"/>
              </FormGroup>
              <FormGroup>
                <Button color="primary" type="submit">Guardar</Button>{' '}
                <Button color="secondary" tag={Link} to={"/telephones/" + this.state.userId}>Cancelar</Button>
              </FormGroup>
            </Form>
          </Container>
        </div>
      }
}

export default telephoneCreateComponent;