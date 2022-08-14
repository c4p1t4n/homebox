import api from "../api"


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
    const url = '/upload/uploadFile';
    const formData = new FormData();
    formData.append('file', state.file);
    const config = {
        headers: {
            'content-type': 'multipart/form-data'
        }
    }
    api.post(url, formData, config)
        .then((response) => {
            console.log(response.data)
        })
}


const onChange = e => {
    state = ({ file: e.target.files[0] })
}


export default TestUploadFile