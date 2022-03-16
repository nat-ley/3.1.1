const user_api = 'http://localhost:8080/api/v1/user/'
const role_api = 'http://localhost:8080/api/v1/role'
const roles_map = new Map();

allUser()
currentUser()
fillRoles()

function allUser() {
    fetch(user_api)
        .then(r => r.json())
        .then(result => {
            console.log(result)
            for (let user of result) {
                let t =
                    `<tr>
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${getRoles(user)}</td>
                        <td>
                            <button class="btn btn-info" 
                                    onclick="fillEditModal(${user.id})" 
                                    type="button" 
                                    data-toggle="modal"
                                    data-target="#modalEdit"
                                    id="table_button_edit">
                            Edit
                           </button>
                        </td>
                        <td>
                            <button class="btn btn-danger" 
                                    onclick="fillDeleteModal(${user.id})"
                                    type="button" 
                                    data-toggle="modal"
                                    data-target="#modalDelete"
                                    id="table_button_delete">
                            Delete
                           </button>
                        </td>
                    </tr>`
                $('#userTable').append(t)
            }
        })
    try {
        document.getElementById('new_user_button').addEventListener(
            'click', addNewUser, false
        );
    } catch (e) {

    }
}

function currentUser() {
    fetch(user_api + 'me')
        .then(response => response.json())
        .then(user => {
            $('#current_user_username').append(user.username)
            $('#current_user_role').append(getRoles(user))

            let t = `
                <tr>
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${getRoles(user)}</td>
                </tr>
            `
            $('#current_user_tbody').append(t)
            $('#user_current_user_tbody').append(t)
        })
}

function addNewUser(e) {
    fetch(user_api, {
        method: 'POST',
        body: JSON.stringify({
            firstName: $('#firstname_new').val(),
            lastName: $('#lastname_new').val(),
            email: $('#email_new').val(),
            password: $('#password_new').val(),
            roles: $('#roles_new').val().map(it => roles_map.get(it)),
        }),
        headers: {'Content-Type': 'application/json'}
    }).then(response => {
        if (response.ok) {
            e.preventDefault()
            $('.nav-tabs a[href="#users-table"]').tab('show');
            updateTable()
        }
    })
}

function fillEditModal(userId) {
    fetch(user_api + userId)
        .then(r => r.json())
        .then(user => {
            $('#id_edit').attr('value', user.id)
            $('#firstname_edit').attr('value', user.firstName)
            $('#lastname_edit').attr('value', user.lastName)
            $('#email_edit').attr('value', user.email)
            document.getElementById('button_edit').addEventListener(
                'click', editUser, false
            );
        })
}

function editUser(e) {
    fetch(user_api, {
        method: 'POST',
        body: JSON.stringify({
            id: $('#id_edit').val(),
            firstName: $('#firstname_edit').val(),
            lastName: $('#lastname_edit').val(),
            email: $('#email_edit').val(),
            password: $('#password_edit').val(),
            roles: $('#roles_edit').val().map(it => roles_map.get(it)),
        }),
        headers: {'Content-Type': 'application/json'}
    }).then(response => {
        if (response.ok) {
            e.preventDefault()
            $('#modalEdit').modal('hide')
            updateTable()
        }
    })
}

function fillDeleteModal(userId) {
    fetch(user_api + userId)
        .then(r => r.json())
        .then(user => {
            $('#id_delete').attr('value', user.id)
            $('#firstName_delete').attr('value', user.firstName)
            $('#lastName_delete').attr('value', user.lastName)
            $('#email_delete').attr('value', user.email)
            document.getElementById('button_delete').addEventListener(
                'click', deleteUser, false
            );
        })
}

function deleteUser(e) {
    fetch(user_api + $('#id_delete').val(), {method: 'DELETE'})
        .then(response => {
            if (response.ok) {
                e.preventDefault()
                $('#modalDelete').modal('hide')
                updateTable()
            }
        })
}

function fillRoles() {
    fetch(role_api)
        .then(r => r.json())
        .then(roles => {
            for (let role of roles) {
                roles_map.set(role.id.toString(), role)
                let roleOption = `<option value=${role.id}> ${role.name} </option>`
                $('#roles_edit').append(roleOption)
                $('#roles_new').append(roleOption)
            }
        })
}

function getRoles(user) {
    return user.roles.map(i => i.name).join(', ');
}

function updateTable() {
    $('#userTable').empty()
    allUser()
}
