import api from "../api"


function TestUploadFile() {
    return (
        <>
            <div>
                <input onChange={onFileChange} id="fileUpload" type="file" name="fileUpload" />
                <br />
                <button id="upload-button" onClick={onFileUpload}>Upload</button>
            </div>
        </>
    )
}

var state = {
    selectedFile: null
}

const onFileChange = event => {
    // Update the state
    state = ({ selectedFile: event.target.files[0] });
}

const onFileUpload = () => {

    // Create an object of formData
    const formData = new FormData();


    // Update the formData object
    formData.append(
        state.selectedFile.name,
        state.selectedFile,
    )

    console.log(formData)

    api.post("/upload/uploadFile", {
        formData
    })
}


export default TestUploadFile