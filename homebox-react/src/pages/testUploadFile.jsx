import api from "../api"
import { v4 as uuidv4 } from 'uuid';

function TestUploadFile() {


    return (
        <>
            <form onSubmit={onFormSubmit}>
                <h1>File Upload</h1>
                <input type="file" onChange={onChange} />
                <br />
                <button type="submit">Upload</button>
            </form>
        </>
    )
}

var state = {
    file: null
}
const onFormSubmit = e => {
    e.preventDefault()
    console.log(state.file)
    const url = '/upload/uploadFile';
    const formData = new FormData();
    formData.append("file", state.file, uuidv4()+".png");
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }
    api.post(url, formData, config)
        .then((response) => {
            console.log(response.status)
        })
}


const onChange = e => {
    state = ({ file: e.target.files[0] })
}


export default TestUploadFile