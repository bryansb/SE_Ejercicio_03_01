import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
// import AppNavbar from './AppNavbar';
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
        // UserService.getUsers().then((response) => {
        //     this.setState({ users: response.data})
            
        // });
        this.setState({isLoading: true});

        fetch('api/users')
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
            <div className="text-center">
                
                <Button size="sm" color="primary" tag={Link} to={"/users/new"}>Crear Usuario</Button>
                <h1>Lista Usuarios</h1>
                <Table className="mt-4">
                    <thead>
                        <tr>
                            <th>DNI</th>
                            <th>Nombre</th>
                            <th>Gestión</th>
                            <th>Telefonos</th>
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
                                    <td><Button size="sm" color="primary" tag={Link} to={"/telephones/" + user.id + "/add"}>Mostrar Teléfonos</Button></td>
                                </tr>
                            )
                        }
                        
                    </tbody>
                </Table>
                
            </div>
        )
    }
}

export default UserComponent;