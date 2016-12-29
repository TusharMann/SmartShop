<?php

error_reporting(-1);
ini_set('display_errors', 'On');

require_once '../include/db_handler.php';
require '.././libs/Slim/Slim.php';

\Slim\Slim::registerAutoloader();

$app = new \Slim\Slim();

// User login

$app->get('/allitems', function() {
    $response = array();
    $db = new DbHandler();

    // fetching all user tasks
    $result = $db->getListOfItemsInStore();

    $response["error"] = false;
    $response["allitems"] = array();

    // pushing single chat room into array
    while ($item = $result->fetch_assoc()) {
        $tmp = array();
        $tmp["itemID"] = $item["itemID"];
        $tmp["itemName"] = $item["itemName"];
        $tmp["itemPrice"] = $item["itemPrice"];
        $tmp["itemQuantity"] = $item["itemQuantity"];
        $tmp["itemCheck"] = $item["itemCheck"];
        array_push($response["allitems"], $tmp);
    }
    echoRespnse(200, $response);
});



$app->post('/user/login', function() use ($app) {
    // check for required params
    verifyRequiredParams(array('name', 'email'));

    // reading post params
    $name = $app->request->post('name');
    $email = $app->request->post('email');

    // validating email address
    validateEmail($email);

    $db = new DbHandler();
    $response = $db->createUser($name, $email);

    // echo json response
    echoRespnse(200, $response);
});


    // check for required params
    $app->post('/calculatebill', function() use ($app) {

    //verifyRequiredParams(array('name','mobile','list'));

    // reading post params
    $name = $app->request->post('name');
    $mobile = $app->request->post('mobile');
    $list = $app->request->post('list');

    //echo $name;
    //$listofitemsarray = explode("_",$nodeValue);

    $db = new DbHandler();
    $response = $db->insertDataIntoUserTransaction($name,$mobile,$list);

    // echo json response
    echoRespnse(200, $response);
});


/* * *
 * Updating user
 *  we use this url to update user's gcm registration id
 */
$app->put('/user/:id', function($user_id) use ($app) {
    global $app;

    verifyRequiredParams(array('gcm_registration_id'));

    $gcm_registration_id = $app->request->put('gcm_registration_id');

    $db = new DbHandler();
    $response = $db->updateGcmID($user_id, $gcm_registration_id);

    echoRespnse(200, $response);
});


/**
 * Verifying required params posted or not
 */
function verifyRequiredParams($required_fields) {
    $error = false;
    $error_fields = "";
    $request_params = array();
    $request_params = $_REQUEST;
    // Handling PUT request params
    if ($_SERVER['REQUEST_METHOD'] == 'PUT') {
        $app = \Slim\Slim::getInstance();
        parse_str($app->request()->getBody(), $request_params);
    }
    foreach ($required_fields as $field) {
        if (!isset($request_params[$field]) || strlen(trim($request_params[$field])) <= 0) {
            $error = true;
            $error_fields .= $field . ', ';
        }
    }

    if ($error) {
        // Required field(s) are missing or empty
        // echo error json and stop the app
        $response = array();
        $app = \Slim\Slim::getInstance();
        $response["error"] = true;
        $response["message"] = 'Required field(s) ' . substr($error_fields, 0, -2) . ' is missing or empty';
        echoRespnse(400, $response);
        $app->stop();
    }
}

/**
 * Validating email address
 */
function validateEmail($email) {
    $app = \Slim\Slim::getInstance();
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $response["error"] = true;
        $response["message"] = 'Email address is not valid';
        echoRespnse(400, $response);
        $app->stop();
    }
}

function IsNullOrEmptyString($str) {
    return (!isset($str) || trim($str) === '');
}

/**
 * Echoing json response to client
 * @param String $status_code Http response code
 * @param Int $response Json response
 */
function echoRespnse($status_code, $response) {
    $app = \Slim\Slim::getInstance();
    // Http response code
    $app->status($status_code);

    // setting response content type to json
    $app->contentType('application/json');

    echo json_encode($response);
}

$app->run();
?>
