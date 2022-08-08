import api from "../api"

function TestUploadFile() {
    return (
        <>
            <div>
                <input id="fileUpload" type="file" name="fileUpload" />
                <br />
                <button id="upload-button" onClick={uploadFile()}>Upload</button>
            </div>
        </>
    )
}

const uploadFile = e => {
    e.preventDefout()
    let inputFile = document?.querySelector("#fileUpload")

    let file = inputFile?.value

    api.post('/upload',{
        file
    })
        
}

export default TestUploadFile