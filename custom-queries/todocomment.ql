/**
 * @id java/examples/todocomment
 * @name TODO comments
 * @kind problem
 * @problem.severity warning
 * @description Finds comments containing the word "TODO"
 * @tags comment
 *       TODO
 */

import java

from JavadocText c
where c.getText().regexpMatch("(?si).*\\bTODO\\b.*")
select c, "TODO comment"
