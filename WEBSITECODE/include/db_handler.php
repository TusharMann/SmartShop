<?php

/**
 * Class to handle all db operations
 * This class will have CRUD methods for database tables
 *
 */
class DbHandler {

    private $conn;

    function __construct() {
        require_once dirname(__FILE__) . '/db_connect.php';
        // opening db connection
        $db = new DbConnect();
        $this->conn = $db->connect();
    }

    // creating new user if not existed
    public function createUser($name, $email) {
        $response = array();

        // First check if user already existed in db
        if (!$this->isUserExists($email)) {
            // insert query
            $stmt = $this->conn->prepare("INSERT INTO users(name, email) values(?, ?)");
            $stmt->bind_param("ss", $name, $email);

            $result = $stmt->execute();

            $stmt->close();

            // Check for successful insertion
            if ($result) {
                // User successfully inserted
                $response["error"] = false;
                $response["user"] = 1;
            } else {
                // Failed to create user
                $response["error"] = true;
                $response["message"] = -1;
            }
        } else {
            // User with same email already existed in the db
            $response["error"] = false;
            $response["user"] = -1;
        }

        return $response;
    }


    // fetching single user by id
    public function getUser($user_id) {
        $stmt = $this->conn->prepare("SELECT user_id, name, email, gcm_registration_id, created_at FROM users WHERE user_id = ?");
        $stmt->bind_param("s", $user_id);
        if ($stmt->execute()) {
            // $user = $stmt->get_result()->fetch_assoc();
            $stmt->bind_result($user_id, $name, $email, $gcm_registration_id, $created_at);
            $stmt->fetch();
            $user = array();
            $user["user_id"] = $user_id;
            $user["name"] = $name;
            $user["email"] = $email;
            $user["gcm_registration_id"] = $gcm_registration_id;
            $user["created_at"] = $created_at;
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }

    // fetching multiple users by ids
    public function getUsers($user_ids) {

        $users = array();
        if (sizeof($user_ids) > 0) {
            $query = "SELECT user_id, name, email, gcm_registration_id, created_at FROM users WHERE user_id IN (";

            foreach ($user_ids as $user_id) {
                $query .= $user_id . ',';
            }

            $query = substr($query, 0, strlen($query) - 1);
            $query .= ')';

            $stmt = $this->conn->prepare($query);
            $stmt->execute();
            $result = $stmt->get_result();

            while ($user = $result->fetch_assoc()) {
                $tmp = array();
                $tmp["user_id"] = $user['user_id'];
                $tmp["name"] = $user['name'];
                $tmp["email"] = $user['email'];
                $tmp["gcm_registration_id"] = $user['gcm_registration_id'];
                $tmp["created_at"] = $user['created_at'];
                array_push($users, $tmp);
            }
        }

        return $users;
    }



    /**
     * Checking for duplicate user by email address
     * @param String $email email to check in db
     * @return boolean
     */
    private function isUserExists($email) {
        $stmt = $this->conn->prepare("SELECT user_id from users WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->store_result();
        $num_rows = $stmt->num_rows;
        $stmt->close();
        return $num_rows > 0;
    }

    /**
     * Fetching user by email
     * @param String $email User email id
     */
    public function getUserByEmail($email) {
        $stmt = $this->conn->prepare("SELECT user_id, name, email, created_at FROM users WHERE email = ?");
        $stmt->bind_param("s", $email);
        if ($stmt->execute()) {
            // $user = $stmt->get_result()->fetch_assoc();
            $stmt->bind_result($user_id, $name, $email, $created_at);
            $stmt->fetch();
            $user = array();
            $user["user_id"] = $user_id;
            $user["name"] = $name;
            $user["email"] = $email;
            $user["created_at"] = $created_at;
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }



    // fetching all chat rooms
    public function getListOfItemsInStore() {
        $stmt = $this->conn->prepare("SELECT * FROM items");
        $stmt->execute();
        $tasks = $stmt->get_result();
        $stmt->close();
        return $tasks;
    }

    public function isExistsTransaction($name, $mobile) {

    }

    public function insertDataIntoUserTransaction($name,$mobile,$list)
    {


        // insert new record

        //echo $name;
        $stmt = $this->conn->prepare("DELETE FROM usertransaction where name = ? and mobile = ? ");
        $stmt->bind_param("ss",$name, $mobile);

        //$stmt = $this->conn->prepare("INSERT INTO usertransaction (name,mobile,list) values($name, $mobile, ?)");
        //$stmt->bind_param("ssssss",$list);
        $result = $stmt->execute();
        $stmt->close();



        $stmt = $this->conn->prepare("INSERT INTO usertransaction (name, mobile, list) values(?, ?, ?)");
        $stmt->bind_param("sss",$name, $mobile, $list);

        //$stmt = $this->conn->prepare("INSERT INTO usertransaction (name,mobile,list) values($name, $mobile, ?)");
        //$stmt->bind_param("ssssss",$list);
        $result = $stmt->execute();


        // Check for successful insertion
        if ($result) {
            // User successfully inserted
            $response["error"] = false;
            //$response["user"] = $this->getUserByEmail($email);
            $response["message"] = "Transaction Added";
        } else {
            // Failed to create user
            $response["error"] = true;
            $response["message"] = "Transaction failed";
        }

        $stmt->close();
        return $response;

    }

}

?>
