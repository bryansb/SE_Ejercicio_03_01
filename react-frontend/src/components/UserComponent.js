import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import { Link } from 'react-router-dom';


class UserComponent extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            users:[]
        };
        this.remove = this.remove.bind(this);
    }

    componentDidMount(){
        this.setState({isLoading: true});

        fetch('/api/users')
            .then(response => response.json())
            .then(data => this.setState({users: data, isLoading: false}));
    }
    

    async remove(id) {
        await fetch(`/api/user/${id}`, {
          method: 'DELETE',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          }
        }).then(() => {
          let updatedUser = [...this.state.users].filter(i => i.id !== id);
          this.setState({users: updatedUser});
        });
    }

    render (){
        const {users, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <div className="container m-5 mx-auto">
                <Button size="sm" color="success" tag={Link} to={"/users/new"} className="float-right">Crear Usuario</Button>
                <div className="text-center">
                    <h1>Lista de Usuarios</h1>
                    <Table className="mt-4">
                        <thead>
                            <tr>
                                <th>Cédula</th>
                                <th>Nombre</th>
                                <th>Gestión</th>
                                <th>Teléfonos</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            {
                                this.state.users.map(
                                    user =>
                                    <tr key = {user.id}>
                                        <td>{user.dni}</td>
                                        <td>{user.name}</td>
                                        <td>
                                            <ButtonGroup>
                                                <Button size="sm" color="primary" tag={Link} to={"/users/" + user.id}>Editar</Button>
                                                <Button size="sm" color="danger" onClick={() => this.remove(user.id)}>Eliminar</Button>
                                            </ButtonGroup>
                                        </td>
                                        <td><Button size="sm" color="primary" tag={Link} to={"/telephones/" + user.id}>Mostrar Teléfonos</Button></td>
                                    </tr>
                                )
                            }
                            
                        </tbody>
                    </Table>
                </div>
            </div>
        )
    }
}

export default UserComponent;