package ass1

enum Tree[K, V] extends TreeInterface[K, V]:
  private case Leaf(key: K, payload: V)
  private case Node(key: K, children: List[Tree[K, V]])

  import Tree.Path

  def contains(path: Path[K]): Boolean = {
    def containsRecur(tree: Tree[K, V], path: Path[K]): Boolean = {
      path.segments match {
        case Nil => true
        case head :: tail => {
          tree match {
            case Leaf(key, _) => {
              key == head 

            } 
            case Node(key, children) => {
              val c = children.find(child => child.key == head)

              c match {
                case Some(s) => {
                  children.exists(child => containsRecur(child, Path[K](tail)))
                }
                case None => false
              }
            }
          }
        }
      }
    }

    containsRecur(this, path)
  }



  def get(path: Path[K]): Option[Either[List[K], V]] = {
    def getRecur(tree: Tree[K, V], path: Path[K]): Option[Either[List[K], V]] = {
      path.segments match {
        case Nil => tree match {
          case Node(key, children) => Some(Left(children.map {
            case Leaf(key, _) => key
            case Node(key, _) => key
          }))
          case Leaf(_, payload) => Some(Right(payload))
        }

        case head :: tail => tree match {
          case Leaf(key, payload) =>
            if (key == head && tail.isEmpty) Some(Right(payload)) else None

          case Node(key, children) =>
            val child = children.find(child => child.key == head)
            child.flatMap(c => getRecur(c, Path[K](tail)))
        }
      }
    }

    getRecur(this, path)
  }


  def flatten: List[(Path[K], V)] = {
    def flattenRecur(tree: Tree[K, V], path: List[K]): List[(Path[K], V)] = {
      tree match {
        case Leaf(key, payload) =>
          List((Path(path :+ key), payload))

        case Node(key, children) =>
          children.flatMap(child => flattenRecur(child, path :+ key))
      }
    }

    flattenRecur(this, Nil)
  }


  def updated(path: Path[K]): Tree[K, V] = {
    def updatedRecur(tree: Tree[K, V], path: Path[K]): Tree[K ,V] = {
      path.segments match {
        case Nil => tree match {
          case Leaf(key, p) => throw Tree.IllegalPathException
          case Node(key, c) => if (c.isEmpty) throw Tree.IllegalPathException else Node(key, c)
        }
        case head :: tail => {
          if (tail.isEmpty) Node(head, Nil) 
          else tree match {
            case Leaf(key, p) => Node(key, Nil)
            case Node(key, children) => {         
              val c = children.find(child => child.key == head)   

              val updatedChildren = c match {
                case Some(s) => { // have children has the key
                  children.map(
                    child => child match {
                      case Leaf(key, payload) => {
                        if (key == s.key) Node(key, List(updatedRecur(Node(tail.head, Nil), Path[K](tail)))) else Leaf(key, payload)
                      }
                      case Node(key, children) => {
                        if (key == s.key) {                       
                          val c = children.find(child => child.key == tail.head)   
                          val x = c match {
                            case Some(s) => children.map(
                              child => if (child.key == tail.head) updatedRecur(Node(tail.head, Nil), Path[K](tail.tail))
                              else child
                            ) 
                            case None => children :+ updatedRecur(Node(head, Nil), Path[K](tail))
                          }

                          Node(key, x)
                        } else child       
                      }
                    }
                  )
                }
                case None => { // no children has the key
                  children :+ Node(head, List(updatedRecur(Node(tail.head, Nil), Path[K](tail))))
                }
              }
              
              Node(key, updatedChildren)
            }
          }
        }
      } 
    }

    updatedRecur(this, path)
  }


  def updated(path: Path[K], payload: V): Tree[K, V] = {   
    def updatedRecur(tree: Tree[K, V], path: Path[K], payload: V): Tree[K ,V] = {
      path.segments match {
        case Nil => tree match {
          case Leaf(key, p) => throw Tree.IllegalPathException
          case Node(key, c) => if (c.isEmpty) throw Tree.IllegalPathException else Node(key, c)
        }
        case head :: tail => {
          if (tail.isEmpty) Leaf(head, payload) 
          else tree match {
            case Leaf(key, p) => Node(key, Nil)
            case Node(key, children) => {         
              val c = children.find(child => child.key == head)   

              val updatedChildren = c match {
                case Some(s) => { // have children has the key
                  children.map(
                    child => child match {
                      case Leaf(key, payload) => {
                        if (key == s.key) Node(key, List(updatedRecur(Node(tail.head, Nil), Path[K](tail), payload))) else Leaf(key, payload)
                      }
                      case Node(key, children) => {
                        if (key == s.key) {                       
                          val c = children.find(child => child.key == tail.head)   
                          val x = c match {
                            case Some(s) => children.map(
                              child => if (child.key == tail.head) updatedRecur(Node(tail.head, Nil), Path[K](tail.tail), payload)
                              else child
                            ) 
                            case None => children :+ updatedRecur(Node(head, Nil), Path[K](tail), payload)
                          }

                          Node(key, x)
                        } else child       
                      }
                    }
                  )
                }
                case None => { // no children has the key
                  children :+ Node(head, List(updatedRecur(Node(tail.head, Nil), Path[K](tail), payload)))
                }
              }
              
              Node(key, updatedChildren)
            }
          }
        }
      } 
    }

    updatedRecur(this, path, payload)
  }

  def display: String = {
    def displayRecur(tree: Tree[K, V], level: Int, prefix: String, connector: String): String = {
      tree match {
        case Leaf(k, v) => s"$prefix$connector Leaf($k, $v)\n"
        case Node(k, children) => 
          val nodeString = s"$prefix$connector Node($k, ...)\n"
          val indexedChildren = children.zipWithIndex
          
          val childrenNodeString = indexedChildren.map { 
            case (child, index) =>
              val childPrefix = prefix + (if (level > 0) (if (connector.startsWith("└")) "    " else "│   ") else "")
              val childConnector = if (index == children.size - 1) "└──" else "├──"
              
              displayRecur(child, level + 1, childPrefix, childConnector)
          }.mkString("")

          nodeString + childrenNodeString
      }
    }
    
    displayRecur(this, level = 0, "", "").trim
  }


object Tree extends TreeComp:
  def apply[K, V](key: K): Tree[K, V] = {
    Node(key, Nil)
  }

  def apply[K, V](path: Path[K]): Tree[K, V] = {
    path.segments match 
      case Nil => throw Tree.IllegalPathException
      case last :: Nil => Node(last, Nil)
      case head :: tail => Node(head, List(Tree[K, V](Path(tail))))
  }

  def apply[K, V](path: Path[K], payload: V): Tree[K, V] = {
    path.segments match 
      case Nil => throw Tree.IllegalPathException      
      case last :: Nil => Leaf(last, payload)
      case head :: tail => Node(head, List(Tree[K, V](Path(tail))))    
  }
